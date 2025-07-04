import java.util.ArrayList;
import java.util.List;

public class ShippingService {
    
    public void ship(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        for (Shippable item : items) {
            double itemWeight = item.getWeight();
            System.out.printf("%s\t%.0fg%n", item.getName(), itemWeight * 1000);
            totalWeight += itemWeight;
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
        System.out.println();
        
        System.out.println("All items have been shipped successfully.");
    }
}
