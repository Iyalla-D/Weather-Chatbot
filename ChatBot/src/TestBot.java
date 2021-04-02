import java.util.Scanner;

public class TestBot {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String userInput = "";
        MyBot chatbot = new MyBot();
 
        while(!userInput.equalsIgnoreCase("stop it")) {
            System.out.print("User: ");
            userInput = sc.nextLine();
            chatbot.answer(userInput);
        }
        sc.close();
    }
	}


