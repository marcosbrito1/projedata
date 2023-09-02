package view;

import controller.PrincipalController;
import javax.swing.JOptionPane;

public class Principal {
    public static void main(String [] args){
        String op = "";
        PrincipalController principalController = new PrincipalController();
        while(!op.equals("0")){
            op = JOptionPane.showInputDialog(null, "1 - Inserir todos os funcionários\n2 - Remover o funcionário João\n3 - Imprimir todos os funcionários de forma detalhada\n4 - Acrescentar 10% ao salário dos funcionários\n5 - Agrupar os funcionários por função em um MAP\n6 - Imprimir os funcionários agrupados por função\n7 - Imprimir os funcionários que fazem aniversário no mês 10 e 12\n8 - Imprimir funcionário com maior idade\n9 - Imprimir Lista de funcionários por ordem alfabética\n10 - Imprimir total do salário dos funcionários\n11 - Imprimir quantos salários minimos ganha cada funcionário\n(0 = Sair)\n\n");
            if(principalController.isValidar(op)){
            principalController.escolherOpcao(op);                
            }
        }
    }
}