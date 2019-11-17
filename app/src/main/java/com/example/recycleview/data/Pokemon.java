package com.example.recycleview.data;

public class Pokemon {
        private String name;
        private int id;
        private int height;
        private int baseExperience;
        private int weight;
        private String types;
        private String image;

        public Pokemon(String name, int id, int height, int baseExperience, int weight, String types, String image) {
                this.name = name;
                this.id = id;
                this.height = height;
                this.baseExperience = baseExperience;
                this.weight = weight;
                this.types = types;
                this.image = image;
        }

        public String getName() {
                return name;
        }

        public int getId() {
                return id;
        }

        public int getHeight() {
                return height;
        }

        public int getBaseExperience() {
                return baseExperience;
        }

        public int getWeight() {
                return weight;
        }

        public String getTypes() {
                return types;
        }

        public String getImage() {
                return image;
        }
}


