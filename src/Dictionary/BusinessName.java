package Dictionary;

public class BusinessName implements Comparable<BusinessName>{
    private String name;

    private String municipality;

    public BusinessName(String name){
//        add a way to seperate muncipality
        if (name.contains(",")){

            String[] section = name.split(",");
            this.name = section[0];

//            System.out.println(this.name);
            this.municipality = section[1];
//            System.out.println(this.municipality);
            return;


        }

        this.name = name;
    }

    public void setMunicipality(String municipality){
        this.municipality = municipality;
    }

    public String getName(){
        return name;
    }


    public String getMunicipality() {
        return municipality;
    }

    public String toString(){
        return null;
    }
    @Override
    public int compareTo(BusinessName o) {
        int lastCmp = name.compareTo(o.name);
        return  (lastCmp != 0 ? lastCmp : name.compareTo(o.name));
    }
}
