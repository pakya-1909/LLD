package FactoryDesignPattern;
// shape example

// factory design pattern we create

interface Shape{
    public void draw();
}

class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("draw a square");
    }
}

class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("draw a circle");
    }
}

class ShapeFactory{

    public Shape getShape(String type){
        if(type.equals("square")){
            return new Square();
        }

        if(type.equals("circle")){
            return new Circle();
        }

        return null;
    }

}

public class Main {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getShape("circle");
        circle.draw();
    }

}
