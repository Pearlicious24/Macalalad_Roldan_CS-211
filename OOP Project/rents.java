import java.util.*;

abstract class Property {
    private int roomId;
    private String roomType;
    private double rentPrice;
    private boolean isOccupied;

    public Property(int roomId, String roomType, double rentPrice) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.rentPrice = rentPrice;
        this.isOccupied = false;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupyRoom() {
        this.isOccupied = true;
    }

    public void vacateRoom() {
        this.isOccupied = false;
    }

    public abstract void displayDetails();
}

class Room extends Property {
    public Room(int roomId, String roomType, double rentPrice) {
        super(roomId, roomType, rentPrice);
    }

    @Override
    public void displayDetails() {
        System.out.println("\nRoom ID: " + getRoomId() + " \nType: " + getRoomType() + " \nPrice: " + getRentPrice() + " \nOccupied: " + isOccupied());
    }
}

class Tenant {
    private String name;
    private String contactNumber;
    private int roomId;

    public Tenant(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.roomId = -1;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getRoomId() {
        return roomId;
    }

    public void assignRoom(int roomId) {
        this.roomId = roomId;
    }

    public void updateProfile(String newName, String newContact) {
        this.name = newName;
        this.contactNumber = newContact;
    }

    public String getFormattedProfile() {
        return  "============================="+
                "\n  --- Tenant Profile ---\n" +
                "============================="+
               "\nRoom ID       : " + (roomId == -1 ? "Not Assigned" : roomId) + "\n" +
               "Name          : " + name + "\n" +
               "Contact Number: " + contactNumber + "\n" +
               "\n============================="+
               "\n    Thank you!"+
               "\n=============================\n";
    }

    @Override
    public String toString() {
        return getFormattedProfile();
    }
}

class RentalManager {
    private List<Room> rooms;
    private Map<Integer, Tenant> roomTenantMap;

    public RentalManager() {
        rooms = new ArrayList<>();
        roomTenantMap = new HashMap<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void listRooms() {
        for (Room room : rooms) {
            room.displayDetails();
        }
    }

    public void rentRoom(int roomId, Tenant tenant) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId && !room.isOccupied()) {
                room.occupyRoom();
                tenant.assignRoom(roomId);
                roomTenantMap.put(roomId, tenant);
                System.out.println("\nRoom rented successfully to " + tenant.getName());
                return;
            }
        }
        System.out.println("\nRoom not available or does not exist.");
    }

    public void vacateRoom(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId && room.isOccupied()) {
                room.vacateRoom();
                Tenant tenant = roomTenantMap.get(roomId);
                if (tenant != null) {
                    tenant.assignRoom(-1);
                }
                roomTenantMap.remove(roomId);
                System.out.println("\nRoom vacated successfully.");
                return;
            }
        }
        System.out.println("\nRoom not occupied or does not exist.");
    }

    public void viewTenant(int roomId) {
        if (roomTenantMap.containsKey(roomId)) {
            System.out.println(roomTenantMap.get(roomId));
        } else {
            System.out.println("\nNo tenant found for Room ID: " + roomId);
        }
    }

    public void updateTenantProfile(int roomId, String newName, String newContact) {
        if (roomTenantMap.containsKey(roomId)) {
            Tenant tenant = roomTenantMap.get(roomId);
            tenant.updateProfile(newName, newContact);
            System.out.println("\nTenant profile updated successfully.");
        } else {
            System.out.println("\nNo tenant found for Room ID: " + roomId);
        }
    }
}

public class rents {
    public static void main(String[] args) {
        RentalManager manager = new RentalManager();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n======================================================");
            System.out.println("  RENTS:RENTAL ESTATE NAVIGATION AND TRACKING SYSTEM");
            System.out.println("======================================================");
            System.out.println("1. Add Room");
            System.out.println("2. List Rooms");
            System.out.println("3. Rent Room");
            System.out.println("4. Vacate Room");
            System.out.println("5. View Tenant");
            System.out.println("6. Update Tenant Profile");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Room Type: ");
                    String roomType = scanner.nextLine();
                    System.out.print("Enter Rent Price: ");
                    double rentPrice = scanner.nextDouble();
                    manager.addRoom(new Room(roomId, roomType, rentPrice));
                    System.out.println("\n========================");
                    System.out.println("Room added successfully.");
                    System.out.println("=========================");
                    break;
                case 2:
                    manager.listRooms();
                    break;
                case 3:
                    System.out.print("\nEnter Room ID to rent: ");
                    int rentRoomId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Tenant Name: ");
                    String tenantName = scanner.nextLine();
                    System.out.print("Enter Tenant Contact: ");
                    String tenantContact = scanner.nextLine();
                    manager.rentRoom(rentRoomId, new Tenant(tenantName, tenantContact));
                    break;
                case 4:
                    System.out.print("\nEnter Room ID to vacate: ");
                    int vacateRoomId = scanner.nextInt();
                    manager.vacateRoom(vacateRoomId);
                    break;
                case 5:
                    System.out.print("\nEnter Room ID to view tenant: ");
                    int viewRoomId = scanner.nextInt();
                    manager.viewTenant(viewRoomId);
                    break;
                case 6:
                    System.out.print("\nEnter Room ID to update tenant profile: ");
                    int updateRoomId = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Enter New Tenant Name: ");
                    String newTenantName = scanner.nextLine();
                    System.out.print("Enter New Tenant Contact: ");
                    String newTenantContact = scanner.nextLine();
                    manager.updateTenantProfile(updateRoomId, newTenantName, newTenantContact);
                    break;
                case 7:
                    System.out.println("\nExiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}
