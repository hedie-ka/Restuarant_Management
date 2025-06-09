package project;

public class Address {
    private String area;
    private String street;
    private String alley;
    private String postalCode;

    public Address(String area, String street, String alley, String postalCode) {
        setArea(area);
        setStreet(street);
        setAlley(alley);
        setPostalCode(postalCode);
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getArea() {
        return this.area;
    }

    public String getStreet() {
        return this.street;
    }

    public String getAlley() {
        return this.alley;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    @Override
    public String toString() {
        return "Area: " + this.area + " Street: " + this.street
                + " Alley: " + this.alley + " PostalCode: " + this.postalCode;
    }
}
