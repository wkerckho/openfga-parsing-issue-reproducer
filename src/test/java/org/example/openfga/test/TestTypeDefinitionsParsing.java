package org.example.openfga.test;

import io.quarkiverse.openfga.client.api.API;
import io.quarkiverse.openfga.client.model.TypeDefinitions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTypeDefinitionsParsing {

    @Test
    public void testModelContainingDirectRelationsOnly() {
        // The following JSON model only contains direct relations and parsing succeeds:
        var jsonModel = """
{
  "schema_version": "1.1",
  "type_definitions": [
    {
      "type": "user",
      "relations": {},
      "metadata": null
    },
    {
      "type": "trip",
      "relations": {
        "owner": {
          "this": {}
        },
        "viewer": {
          "this": {}
        }
      },
      "metadata": {
        "relations": {
          "owner": {
            "directly_related_user_types": [
              {
                "type": "user"
              }
            ]
          },
          "viewer": {
            "directly_related_user_types": [
              {
                "type": "user"
              }
            ]
          }
        }
      }
    }
  ]
}
                """;
        Assertions.assertDoesNotThrow(() -> API.createObjectMapper().readValue(jsonModel, TypeDefinitions.class));
    }

    @Test
    public void testModelContainingPermissions() {
        // Parsing the following JSON into TypeDefinitions fails (because it contains indirect relations)
        var jsonModel = """
                         {
                           "schema_version": "1.1",
                           "type_definitions": [
                             {
                               "type": "user",
                               "relations": {},
                               "metadata": null
                             },
                             {
                               "type": "trip",
                               "relations": {
                                 "owner": {
                                   "this": {}
                                 },
                                 "viewer": {
                                   "this": {}
                                 },
                                 "booking_adder": {
                                   "computedUserset": {
                                     "relation": "owner"
                                   }
                                 },
                                 "booking_viewer": {
                                   "union": {
                                     "child": [
                                       {
                                         "computedUserset": {
                                           "relation": "viewer"
                                         }
                                       },
                                       {
                                         "computedUserset": {
                                           "relation": "owner"
                                         }
                                       }
                                     ]
                                   }
                                 }
                               },
                               "metadata": {
                                 "relations": {
                                   "owner": {
                                     "directly_related_user_types": [
                                       {
                                         "type": "user"
                                       }
                                     ]
                                   },
                                   "viewer": {
                                     "directly_related_user_types": [
                                       {
                                         "type": "user"
                                       }
                                     ]
                                   },
                                   "booking_adder": {
                                     "directly_related_user_types": []
                                   },
                                   "booking_viewer": {
                                     "directly_related_user_types": []
                                   }
                                 }
                               }
                             }
                           ]
                         }       
                """;
        Assertions.assertDoesNotThrow(() -> API.createObjectMapper().readValue(jsonModel, TypeDefinitions.class));
    }

}
