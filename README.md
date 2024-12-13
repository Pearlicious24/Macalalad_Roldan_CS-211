# Macalalad_Roldan_CS-211
# Rents: Rental Estate Navigation and Tracking System
The project is a Java-based rental management application for small businesses intended to make room rental procedures easier. It enables a landlord to manage his estate and tenants in a proper manner with transparency and straightforwardness in the operations. Add rooms, list room availability, rent rooms, vacate rooms, view tenant, and update tenant profiles are all functionalities in the application.
# OOP Principles
Encapsulation:
Private fields: All sensitive data in classes like Property, Room, and Tenant are declared private and accessed through public getters and setters to maintain integrity.
Controlled updates: There are specific methods to make only the intended changes to internal states. For example, assignRoom in Tenant or occupyRoom in Property.

Abstraction:
The Property class is abstract, which means it is a blueprint for common attributes and behaviors of properties, such as roomId, rentPrice, and displayDetails. The Room subclass implements the specific behavior for rental rooms. Tenant and rental management methods encapsulate complex business logic, such as mapping tenants to rooms.

Inheritance:
The Room class inherits from the abstract Property class, reusing its fields and methods while adding specific behavior. This reduces code duplication and improves scalability.

Polymorphism:
Method overriding: The Room class overrides the displayDetails method to provide a customized implementation for displaying room information. The toString method in the Tenant class is overridden to format tenant profiles in a user-friendly way.
