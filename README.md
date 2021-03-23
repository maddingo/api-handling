# API Consumption and Generation

Current state: REST API and hopefully some tests. OpenAPI spec is exposed at runtime.


Problems:
1. How does a client consume the service?
   1. pick up the openapi.yaml from the server
   1. copy the contract to the source code.
    
1. How is the contract updated and the client synchronized?
   1. Different servers might expose different versions of the contract.
   1. What is the version of the contract?

1. The client code, Model and endpoint clients are usually created manually. 
1. The model classes are duplicated in each client.

Goals:
1. Publish released openapi.yaml on Maven Repo.
1. generate the client and model code from the contract.
1. Evolve contracts and implementations independently
1. support both code-first and contract-first approach

Next step: 02_publish_contract