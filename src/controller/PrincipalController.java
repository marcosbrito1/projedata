package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import model.Funcionario;

public class PrincipalController {
    List<Funcionario> listFuncionario = new ArrayList();
    List<Funcionario> listAniversariantes = new ArrayList();
    DecimalFormat df = new DecimalFormat("#,##0.00");
    Map<String, List<Funcionario>> funcionariosPorFuncao;
    boolean delJoao = false;
    
    
    public void escolherOpcao(String opcao){
        switch(opcao){
            case "0": break;
            case "1": inserirFuncionarios(); break;
            case "2": removerFuncionarioJoao(); break;
            case "3": imprimirTodosFuncionarios(); break;
            case "4": acrescentarDezPorcentoAoSalario(); break;
            case "5": agruparFuncionariosPorFuncao(); break;
            case "6": imprimirFuncionariosPorFuncao(); break;
            case "7": imprimirFuncionariosAniversariantesMesDezEMesDose(); break;
            case "8": imprimirFuncionarioMaiorIdade(); break;
            case "9": imprimirFuncionariosPorOrdemAlfabetica(); break;
            case "10": imprimirTotalSalarios(); break;
            case "11": imprimirSalariosMinimosPorFuncionario(); break;
        }
    }  
        
    public void inserirFuncionarios(){
      listFuncionario.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("2009.44"), "Operador"));
      listFuncionario.add(new Funcionario("João", LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("2284.38"), "Operador"));
      listFuncionario.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("9836.14"), "Coordenador"));
      listFuncionario.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("19119.88"), "Diretor"));
      listFuncionario.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("2234.68"), "Recepcionista"));
      listFuncionario.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("1582.72"), "Operador"));
      listFuncionario.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("4071.84"), "Contador"));
      listFuncionario.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("3017.45"), "Gerente"));
      listFuncionario.add(new Funcionario("Heloisa", LocalDate.parse("24/05/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("1606.85"), "Eletricista"));
      listFuncionario.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new BigDecimal("2799.93"), "Gerente"));
      JOptionPane.showMessageDialog(null,"Funcionários inseridos na lista com sucesso!");
    }
    
    public void removerFuncionarioJoao(){       
       for(int i = 0; i<listFuncionario.size(); i++){
           if(listFuncionario.get(i).getNome().equals("João")){
               listFuncionario.remove(i);
           }
       }
       delJoao = true;
       JOptionPane.showMessageDialog(null,"Funcionário João removido!");
    }
    
    public void imprimirTodosFuncionarios(){
        listFuncionario.forEach((funcionario) -> {
            JOptionPane.showMessageDialog(null,"Nome: "+funcionario.getNome()+"\nData de Nascimento: "+funcionario.getDataNasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\nSalário: "+df.format(funcionario.getSalario())+"\nFunção: "+funcionario.getFuncao()+"\n\n");
        });
    }
    
    public void acrescentarDezPorcentoAoSalario(){
        listFuncionario.forEach((funcionario) -> {
            funcionario.setSalario((funcionario.getSalario().multiply(new BigDecimal("0.10"))).add(funcionario.getSalario()));
        });
        JOptionPane.showMessageDialog(null,"Aumento de 10% no salário realizado com sucesso!");
    }
    
    public void agruparFuncionariosPorFuncao(){
     funcionariosPorFuncao = listFuncionario.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));   
     JOptionPane.showMessageDialog(null,"Funcionários agrupados por função!");
    }
    
    public void imprimirFuncionariosPorFuncao(){
        String texto;
             for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
                 texto = entry.getKey() + ":\n";
            for (Funcionario funcionario : entry.getValue()) {
                texto=texto+"- "+funcionario.getNome()+"\n";
            }
            JOptionPane.showMessageDialog(null, texto);
        }
    }
    
    public void imprimirFuncionariosAniversariantesMesDezEMesDose(){
        for(int i=0;i < listFuncionario.size(); i++){
            if(listFuncionario.get(i).getDataNasc().getMonthValue() == 10 || listFuncionario.get(i).getDataNasc().getMonthValue() == 12){
              listAniversariantes.add(listFuncionario.get(i));  
            }
        }        
        listAniversariantes.forEach((aniversariante) -> {
            JOptionPane.showMessageDialog(null,"Nome: "+aniversariante.getNome()+"\nMês do Aniversário: "+aniversariante.getDataNasc().getMonthValue());
        });
    }
    
    public void imprimirFuncionarioMaiorIdade(){
        int maior = 0;
        String nome = "";
        for(int i =0; i< listFuncionario.size(); i++){
            if(i==0){
                maior = LocalDate.now().compareTo(listFuncionario.get(i).getDataNasc());
                 nome = listFuncionario.get(i).getNome();
            }else{
            if(LocalDate.now().compareTo(listFuncionario.get(i).getDataNasc())>maior){
                maior = LocalDate.now().compareTo(listFuncionario.get(i).getDataNasc());
                nome = listFuncionario.get(i).getNome();
            }
            }
        }
        JOptionPane.showMessageDialog(null, "Nome: "+nome+"\nIdade: "+maior);
    }
    
    public void imprimirFuncionariosPorOrdemAlfabetica(){
        List<Funcionario> listOrdenado = listFuncionario;
        Collections.sort(listOrdenado, Comparator.comparing(Funcionario::getNome));
        listOrdenado.forEach((funcionario) -> {
            JOptionPane.showMessageDialog(null,"Nome: "+funcionario.getNome()+"\nData de Nascimento: "+funcionario.getDataNasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"\nSalário: "+df.format(funcionario.getSalario())+"\nFunção: "+funcionario.getFuncao()+"\n\n");
        });
    }
    
    public void imprimirTotalSalarios(){
       BigDecimal total = new BigDecimal("0.00"); 
        for(int i=0;i<listFuncionario.size();i++){
            total = total.add(listFuncionario.get(i).getSalario());           
        }
         JOptionPane.showMessageDialog(null, "Total de todos os salários: "+df.format(total));
    }
    
    public void imprimirSalariosMinimosPorFuncionario(){
        BigDecimal total = new BigDecimal("0.00");
        for(int i=0;i<listFuncionario.size();i++){
            JOptionPane.showMessageDialog(null, "Nome: "+listFuncionario.get(i).getNome()+"\nQuantidade de salários minimos: "+(listFuncionario.get(i).getSalario().divide(new BigDecimal("1212.00"),2, RoundingMode.HALF_UP)));
        }
        
    }
    
    public boolean isValidar(String opcao){
        try{
            if(Integer.parseInt(opcao)>11 || Integer.parseInt(opcao)<0){JOptionPane.showMessageDialog(null,"Opção Inválida!"); return false;}
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Opção Inválida!"); return false;   
        }
        if((!opcao.equals("0") && !opcao.equals("1")) && listFuncionario.isEmpty()){JOptionPane.showMessageDialog(null,"Para realizar essa operação é necessário inserir os funcionários na lista!"); return false;}
        if(opcao.equals("6") && funcionariosPorFuncao == null){JOptionPane.showMessageDialog(null,"Para realizar essa operação é necessário agrupar os funcionários por função!"); return false;}
        if(opcao.equals("2") && delJoao){JOptionPane.showMessageDialog(null,"O João já havia sido removido!"); return false;}
       return true;
    }
}