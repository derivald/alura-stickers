import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_4v5erznp";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=uDWCcOusMrrMQzt6cnrfqshgwdnnYUFGLkoNas3u&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url= "https://alura-linguagens-api3.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ///ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
       
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            // exibir e manipular os dados
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }
    }

}