package com.ifpb.sisride.controle;

import com.ifpb.sisride.dao.UsuarioDao;
import com.ifpb.sisride.factory.DaoFactory;
import com.ifpb.sisride.factory.DaoFactoryIF;
import com.ifpb.sisride.modelo.Usuario;
import java.sql.SQLException;
import java.time.LocalDate;

public class GerenciadorUsuario {

    private DaoFactoryIF fabrica = null;
    private UsuarioDao userDao = null;

    public GerenciadorUsuario() throws SQLException, ClassNotFoundException {
        fabrica = DaoFactory.createFactory();
        userDao = fabrica.criaUsuarioDao();
    }

    public boolean adicionaUsuario(String email, String senha, String nome,
            LocalDate nascimento, String profissao, String cidade, String sexo)
            throws SQLException, Exception {
        Usuario u = new Usuario(email, senha, nome, nascimento, profissao, cidade, sexo);
        return userDao.salvar(u);
    }

    public boolean removeUsuario(String email) throws SQLException {
        return userDao.deletar(email);
    }

    public boolean atualizaUsuario(String email, String senha, String nome,
            LocalDate nascimento, String profissao, String cidade, String sexo)
            throws SQLException {
        Usuario u = new Usuario(email, senha, nome, nascimento, profissao, cidade, sexo);
        return userDao.atualizar(u);
    }

    public Usuario buscaUsuario(String email) throws SQLException {
        return userDao.buscar(email);
    }
    
    public boolean autenticar(String email, String senha) throws SQLException{
            return userDao.autenticar(email, senha);
}
}