package WildCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class test {

    static void main(String[] args) {
        List<? extends Animal> list1 = new ArrayList<>();
//        list1.add(new Dog());//compiler does not know which type it is maybe Cat, so it is compilation error
//        list1.add(new Animal());//the reason is the same before
//        list1.add((?)(new Animal()));//? is not a type, so it cannot be used in casting!!
        Animal animal = list1.get(0);
//        Dog dog = list1.get(0);//compilation error

        List<? extends Animal> list2 = new ArrayList<Animal>();
        list2.add(null);//it is ok


        List<? super Dog> list3 = new ArrayList<>();
        list3.add(new Dog());
//        list3.add(new Animal());//maybe the type is Dog, but Animal might not be a Dog but a Cat, so it complains
        Object dog = list3.get(0);
//        Animal animal1 = list3.get(0);
//        Dog dog1=list3.get(0);

        List<? super Animal> list4 = new ArrayList<>();
        list4.add(new Animal());
        list4.add(new Dog());//compiler knows that Dog must be an Animal, so it works
        list4.add(new Cat());


    }

    public static class Animal {
    }

    public static class Dog extends Animal {
    }

    public static class Cat extends Animal {
    }
}
