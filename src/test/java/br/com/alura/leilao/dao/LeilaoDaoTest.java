package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.LeilaoBuilder;
import br.com.alura.leilao.util.UsuarioBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoDaoTest{

    private LeilaoDao dao;
    private EntityManager em;

    @Before
    public void beforeEach(){
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @After
    public void afterEach(){
        em.getTransaction().rollback();
    }

    //---------Métodos auxiliares--------------------

    private Usuario criarUsuario(){
        Usuario usuario = new Usuario("fulano","fulano@email.com","12345678");
        em.persist(usuario);
        return usuario;
    }


    //---------------------TESTES---------------------
    @Test
    public void deveCadastrarUmLeilao(){
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("189237").criar();

        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        Assert.assertNotNull(salvo);
    }

    @Test
    public void deveriarAtualizarUmLeilao(){
        Usuario usuario = criarUsuario();
        Leilao leilao = new Leilao(
                "Mochila",
                new BigDecimal("32938"),
                LocalDate.now(),
                usuario
        );

        leilao = dao.salvar(leilao);

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400"));

        dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        Assert.assertEquals("Celular",salvo.getNome());
        Assert.assertEquals(new BigDecimal("400"),salvo.getValorInicial());
    }
}
