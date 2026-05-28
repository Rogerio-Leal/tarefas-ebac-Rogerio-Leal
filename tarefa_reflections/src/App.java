public class App {

    public static void main(String[] args) {

        Class<?> clazz = usandoAnnotation.class;

        if(clazz.isAnnotationPresent(Tabela.class)) {
            Tabela anotacaoTabela = clazz.getAnnotation(Tabela.class);

            String nomeDaTabela = anotacaoTabela.value();
            System.out.println("Anotação: " + nomeDaTabela);
        } else {
            System.out.println("A classe não possui nenhuma annotation.");
        }
    }
}
