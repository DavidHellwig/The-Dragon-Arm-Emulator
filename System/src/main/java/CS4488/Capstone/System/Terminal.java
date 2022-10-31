package CS4488.Capstone.System;

import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.HexadecimalConverter;
import org.apache.tools.ant.taskdefs.condition.Or;

import java.util.Scanner;

public class Terminal {
    public static void main(){
        boolean keepGoing = true;
        Scanner in = new Scanner(System.in);
        Orchestrator o = Orchestrator.getInstance();
        while (keepGoing){
            System.out.println("Options:\n" +
                    "0. exit\n" +
                    "1. Translate and load Directory\n" +
                    "2. Next\n" +
                    "3. Set Input\n" +
                    "4. Clear Program\n");
                    int a = in.nextInt();
                    switch (a){
                        case 0:
                            keepGoing = false;
                            break;
                        case 1:
                            String s = in.nextLine();
                            o.translateAndLoad(s);
                            o.getProgramState().printProgramState();
                            break;
                        case 2:
                            o.next();
                            o.getProgramState().printProgramState();
                            break;
                        case 3:
                            String hex = in.nextLine();
                            o.getProgramState().input= new Hex4digit(hex);
                            break;
                        case 4:
                            o.clearProgram();
                            break;
                        default:
                            break;
                    }
        }
    }
}
