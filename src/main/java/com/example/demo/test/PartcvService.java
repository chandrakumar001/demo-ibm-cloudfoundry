package com.example.demo.test;

public interface PartcvService {
}

class DefaultPartcvService implements PartcvService {
}

class SimplePartcvService implements PartcvService {
}

//-------------------------------------------------------
interface DataService {
}

final class MySqlDataService implements DataService {
}

final class PostgresDataService implements DataService {
}

final class OracelDataService implements DataService {
}

interface EmployeeService {
}

final class FullTimeEmployeeService implements EmployeeService {
}

final class PartTimeEmployeeService implements EmployeeService {
}

final class InternalEmployeeService implements EmployeeService {
}

final class ExternalEmployeeService implements EmployeeService {
}

final class DefaultEmployeeService implements EmployeeService {
}

final class BasicEmployeeService implements EmployeeService {
}

interface TravelService {
}

final class CarTravelService implements TravelService {
}

final class BikeTravelService implements TravelService {
}

final class BusTravelService implements TravelService {
}


// here,we are the responsible for creating object using 'new'
// and we have to tell, this object has  to inject...
// this way injecting is called 'Tightly coupled'
class TravelController {
    private TravelService travelService = new CarTravelService();
}

// Spring IOC responsible for creating , inject object and maintain life cycle
// this way injecting is called 'loosely coupled'
class TravelController1 {
    private TravelService travelService;
}


class CustomerService{
    //create,read/update/delete/getCustomers customer
    //
}
