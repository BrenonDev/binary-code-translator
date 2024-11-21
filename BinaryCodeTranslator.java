import java.util.Scanner;

public class BinaryCodeTranslator {

    public static void main(String[] args) {
        
        Scanner scn = new Scanner(System.in);

        String textInput = "";

        String codeInput = "";

        int intOperator = 0;

        int bitCounter = 0;

        int byteCounter = 0;

        int power = 0;

        int binaryCode[][];

        String textOutput = "";

        String codeOutput = "";

        char option = ' ';

        String menu = """
            
            ╔════════════════════════════════════════╗
            ║                                        ║
            ║       Tradutor de Código Binário       ║
            ║      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯      ║
            ║  Esta é uma ferramenta para converter  ║
            ║  uma cadeia de caracteres para código  ║
            ║  binário e vice-versa.                 ║
            ║                                        ║
            ║  Selecione uma opção para conversão:   ║
            ║                                        ║
            ║  [1] Texto para Código Binário         ║
            ║  [2] Código Binário para Texto         ║
            ║  [0] Sair                              ║
            ║               ╔════════════════════════╣
            ║               ║ </Developed by Brenon> ║
            ╚═══════════════╩════════════════════════╝

            Opção =>""" + " ";

        while (true) {

            System.out.print(menu);
            option = scn.nextLine().charAt(0);

            switch (option) {
                case '1':
                    System.out.println("\n");
                    System.out.println("╔══════════════════════╦════════════════════════════════════════════════════════════╗");
                    System.out.println("║ Texto para Codificar ║ A seguir informe o texto para converter em código binário. ║");
                    System.out.println("╚══════════════════════╩════════════════════════════════════════════════════════════╝");
                    System.out.println("\n");

                    System.out.print("Texto => ");
                    textInput = scn.nextLine();
                    
                    bitCounter = textInput.length();
                    binaryCode = new int[bitCounter][8];

                    for(int i = 0; i < bitCounter; i++){

                        intOperator = textInput.charAt(i);

                        for(int j = 7; j >= 0; j--){
                            
                            if (intOperator % 2 == 1) {
                                intOperator = (intOperator - 1) / 2;
                                binaryCode[i][j] = 1;
                            }
                            else{
                                intOperator = intOperator / 2;
                                binaryCode[i][j] = 0;
                            }
                        }
                    }

                    for(int i = 0; i < bitCounter; i++){

                        for(int j = 0; j < 8; j++){

                            codeOutput += binaryCode[i][j];
                        }

                        codeOutput += " ";
                    }

                    System.out.println("\n\nCódigo => " + codeOutput + "\n");
                    codeOutput = "";
                    break;
            
                case '2':
                    System.out.println("\n");
                    System.out.println("╔══════════════════════════╦════════════════════════════════════════════════════════════╗");
                    System.out.println("║ Binário para Decodificar ║ A seguir informe o código binário para converter em texto. ║");
                    System.out.println("╚══════════════════════════╩════════════════════════════════════════════════════════════╝");
                    System.out.println("\n");

                    do{
                        System.out.print("Código => ");
                        codeInput = scn.nextLine().replaceAll("\\s","");

                        bitCounter = codeInput.length();

                        if (!codeInput.matches("[01]+")) {
                            System.out.println("\nEntrada inválida. O código binário deve conter apenas '0' e '1'.\n");
                        }
                        else if (bitCounter % 8 != 0) {
                            System.out.println("\nEntrada inválida. O código binário deve conter conjuntos de 8 bits.\n");
                        }

                    }while (bitCounter % 8 != 0 || !codeInput.matches("[01]+"));
                    
                    byteCounter = (bitCounter / 8);
                    binaryCode = new int[byteCounter][8];
                    bitCounter = 0;

                    for(int i = 0; i < byteCounter; i++){

                        for(int j = 0; j < 8; j++){

                            binaryCode[i][j] = Character.getNumericValue(codeInput.charAt(bitCounter));
                            bitCounter++;
                        }
                    }

                    for(int i = 0; i < byteCounter; i++){

                        intOperator = 0;
                        power = 7;

                        for(int j = 0; j < 8; j++){

                            intOperator += binaryCode[i][j] * Math.pow(2, power);
                            power--;
                        }

                        textOutput += Character.toString((char) intOperator);
                    }

                    System.out.println("\n\nTexto => " + textOutput + "\n");
                    textOutput = "";
                    break;

                case '0':
                System.out.println("\nSaindo...\n");
                    scn.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        }
    }
}
