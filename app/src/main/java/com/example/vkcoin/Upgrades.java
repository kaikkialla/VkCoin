package com.example.vkcoin;

public class Upgrades {

    private int id;
    private String name;
    private float price;
    private float gain;

    public Upgrades(int id, String name, float price, float gain) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gain = gain;
    }


    public int getid() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public float getGain() {
        return gain;
    }


//    private CPU cpu;
//
//    private SuperPC superpc;
//
//
//    public CPU getCpu() {
//        return cpu;
//    }
//
//
//    public SuperPC getSuperpc() {
//        return superpc;
//    }
//
//
//
//
//
//    public class CPU  extends Bonus{
//        private String name = "Видеокарта";
//        private float price;
//        private float gain;
//        private float quantity;
//
//
//        public String getName() {
//            return name;
//        }
//
//        public float getPrice() {
//            return price;
//        }
//
//        public float getGain() {
//            return gain;
//        }
//
//        public float getQuantity() {
//            return quantity;
//        }
//
//        public void setPrice(float price) {
//            this.price = price;
//        }
//
//        public void setGain(float gain) {
//            this.gain = gain;
//        }
//
//        public void setQuantity(float quantity) {
//            this.quantity = quantity;
//        }
//    }
//
//
//    public class SuperPC  extends Bonus{
//        private String name = "Суперкомпьютер";
//        private float price;
//        private float gain;
//        private float quantity;
//
//
//        public String getName() {
//            return name;
//        }
//
//        public float getPrice() {
//            return price;
//        }
//
//        public float getGain() {
//            return gain;
//        }
//
//        public float getQuantity() {
//            return quantity;
//        }
//
//        public void setPrice(float price) {
//            this.price = price;
//        }
//
//        public void setGain(float gain) {
//            this.gain = gain;
//        }
//
//        public void setQuantity(float quantity) {
//            this.quantity = quantity;
//        }
//    }

}
