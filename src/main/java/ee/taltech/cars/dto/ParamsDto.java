package ee.taltech.cars.dto;

import java.util.ArrayList;
import java.util.List;

public class ParamsDto {

    private List<String> bodyType = new ArrayList<>();
    private List<String> model = new ArrayList<>();
    private List<String> brand = new ArrayList<>();
    private List<String> fuel = new ArrayList<>();
    private List<String> gearBoxType = new ArrayList<>();
    private List<String> driveType = new ArrayList<>();
    private List<String> color = new ArrayList<>();
    private List<String> location = new ArrayList<>();

    public void addBodyType(String bodyType) {
        this.bodyType.add(bodyType);
    }
    public void addModel(String model) {
        this.model.add(model);
    }
    public void addBrand(String brand) {
        this.brand.add(brand);
    }
    public void addFuel(String fuel) {
        this.fuel.add(fuel);
    }
    public void addGearBoxType(String gearBoxType) {
        this.gearBoxType.add(gearBoxType);
    }
    public void addDriveType(String driveType) {
        this.driveType.add(driveType);
    }
    public void addColor(String color) {
        this.color.add(color);
    }
    public void assLocation(String location) {
        this.location.add(location);
    }
    public List<String> getBodyType() { return this.bodyType; }
    public List<String> getBrand() { return this.brand; }
    public List<String> getModel() { return this.model; }
    public List<String> getFuel() { return this.fuel; }
    public List<String> getGearBoxType() { return this.gearBoxType; }
    public List<String> getDriveType() { return this.driveType; }
    public List<String> getColor() { return this.color; }
    public List<String> getLocation() { return this.location; }
}
