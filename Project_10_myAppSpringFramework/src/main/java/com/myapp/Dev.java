package com.myapp;

public class Dev {

    private Computer computer;

    //For Constructor Injection
    public Dev(Computer computer){
        this.computer = computer;
        System.out.println("Dev 1 constructor");
    }

    // For Setter Injection
    public Computer getComputer() {
        return computer;
    }
    public void setComputer(Computer computer) {
        this.computer = computer;
    }



    private int age;

    // For Constructor Injection
    public Dev(int age){
        this.age = age;
        System.out.println("Parametrized constructor");
    }

    // For Setter Injection
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public Dev(){
        System.out.println("Dev constructor ...");
    }
    

    public void build(){

        System.out.println( "\nMil jaaoon main tumhein agar raaston mein kahin\r\n" + //
                            "Tum rasman hi pooch lena mujhse haal mera\r\n" + //
                            "Main muskura kar, gham chhupa kar hi jawaab doonga\r\n" + //
                            "Main yahi karta aaya hoon, ye hai kamaal mera\n");

        computer.compile();
    }

}
