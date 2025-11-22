# Second task for backendprogramming part 2


Run the bash-script "build_and_run" to package the project and run the .jar.

The application initializes with a set of sample data. If the database already contains any data the initialization is skipped.


## Supported endpoints

The application runs on localhost:8080 by default.

Create customer:  POST  /customers?name=Anna&phone=123456789

Get customer Id by name:  GET   /customer-id?name=Anna

All customers and their vehicles:   GET  /customers

Create vehicle:  POST  /vehicles?registrationNumber=DEF321&brand=Volvo&model=V70&productionYear=2007

All vehicles:  GET  /vehicles

Add a customer as owner to a vehicle: POST /vehicle-to-customer?customerId=3&vehicleId=1552

List vehicles of brand:  GET  /vehicles-by-brand?brand=Volvo
