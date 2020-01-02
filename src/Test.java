public class Test {

	public static void main(String[] args) {
		
		//Create the object
		InfixEvaluation testObj = new InfixEvaluation();
		
		//call the evaluate method and pass the expression as string
		//test case 1 - 2+8-1*4/1
		String testExpression1 = "2+8-1*4/1";
		System.out.println(testObj.evaluate(testExpression1));
		
		//Lets add braces and decimals
		//test case 2 - 4+(1.4*2.1)+44-21.2
		System.out.println(testObj.evaluate("4+(1.4*2.1)+44-21.2"));
		
		/*Output of above two test cases - 
		 * Test case 1 - 6.0
		 * Test case 2 - 29.740000000000002
		 * */
	}
}
