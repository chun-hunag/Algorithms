import java.util.Scanner;
import java.util.Stack;

public class Evaluation {
	public static void main(String[] args)
	{
		Stack<String> operators = new Stack<String>();
		Stack<Double> values = new Stack<Double>();
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        for (String str : strings) {
			String val = str;
			switch (val) {
				case "(" :
					break;
				case "+" :
					operators.push(val);
					break;
				case "-" :
					operators.push(val);
					break;
				case "*" :
					operators.push(val);
					break;
				case "/" :
					operators.push(val);
					break;
				case "sqrt" :
					operators.push(val);
					break;
				case ")" :
				{
					String operator = operators.pop();
					double value = values.pop();
					switch (operator) {
						case "+" :
							value = values.pop() + value;
							break;
						case "-" :
							value = values.pop() - value;
							break;
						case "*" :
							value = values.pop() * value;
							break;
						case "/" :
							value = values.pop() / value;
							break;
						case "sqrt" :
							value = Math.sqrt(value);
							break;
					}
					values.push(value);
					break;
				}
					
				default :
					double num;
					try {
						num = Double.parseDouble(val);
						values.push(num);
					} catch (NumberFormatException  e) {
						e.printStackTrace();
					}
			}
        }
		System.out.println("Result: " + values.pop());
	}
}
