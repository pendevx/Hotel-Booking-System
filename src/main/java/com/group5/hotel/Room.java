package com.group5.hotel;

public class Room implements Comparable<Room> {
	// add access stuff
    int floor;
    char roomNum;
    double price;

    public Room(int floor, char roomNum) {
        this.floor = floor;
        this.roomNum = roomNum;
        this.price = calculatePrice();
    }

    private double calculatePrice() { return 100d; }
    public String getRoomNumber() { return floor + "" + roomNum; }
    public int getFloor() { return floor; }
    public char getRoomNum() { return roomNum; }

    @Override
    public int compareTo(Room o) {
        if (floor < o.floor) return -1;
        else if (floor > o.floor) return 1;
        else return 0;
    }

    public boolean equals(Room o) { return floor == o.floor && roomNum == o.roomNum; }
}