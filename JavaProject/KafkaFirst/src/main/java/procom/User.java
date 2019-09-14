package procom;



public class User{
    private int id=0;
    private String name="";

    public  User(int id,String name) {
        this.id = id;
        this.name = name;
    }
    public  String toString(){
        return "id="+getId()+" name="+getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
