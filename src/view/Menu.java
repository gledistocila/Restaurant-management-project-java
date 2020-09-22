package view;
import java.util.Scanner;
import model.*;


public class Menu {
	Restaurant juvenilja = new Restaurant("Juvenilja");
	
	Food food1 = new Food(1, "Pilaf", 100, "Mengjes");
	Food food2 = new Food(2, "Omelete", 200, "Mengjes");
	Food food3 = new Food(3, "Kos dele", 100, "Mengjes");
	Food food4 = new Food(4, "Tost me veze dhe avokado", 250, "Mengjes");
	Food food5 = new Food(5, "Oatmeal", 170, "Mengjes");
	Food food6 = new Food(6, "Tost me humus", 160, "Mengjes");
	Food food7 = new Food(7, "Shakshuka", 220, "Dreke");
	Food food8 = new Food(8, "Gjelle me perime", 180, "Dreke");
	Food food9 = new Food(9, "Supe brokoli", 140, "Dreke");
	Food food10 = new Food(10, "Pica", 300, "Darke");
	Food[] food = {food1, food2, food3, food4, food5, food6, food7, food8, food9, food10};
	
	int orderCount = 0;
	
	public void start() {
		System.out.println("Welcome to " +juvenilja.getRestaurantName()+ " ! ");
		Scanner input = new Scanner(System.in);
			   System.out.println("\n\n  Reservation Menu  \n\n");
			   
			   for(int i=0; i<10; i++) {
				   System.out.println("Nr." +(i+11));
				   System.out.println(food[i].toString());
			   }
			   System.out.println("Nese keni vendosur te jepni porosine, konfirmoni duke shtypur 1 : \n");
			   if(input.nextInt()==1) {
				  
			   }
			   
		input.close();
}
}
