/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Usuario;
import com.miraflorescarwash.model.UsuarioRol;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDao<Long, Usuario> implements UsuarioDAO {

    @Override
    public Usuario findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public Usuario findByCredenciales(Usuario usuario) {
        String consulta;
        Query query;
        consulta = "SELECT u FROM Usuario u WHERE u.password=:password AND u.username=:username";
        query = super.getSession().createQuery(consulta);
        query.setParameter("password", usuario.getPassword());
        query.setParameter("username", usuario.getUsername());
        return (Usuario) query.uniqueResult();
    }

    @Override
    public List<Usuario> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<Usuario>) criteria.list();
    }

    @Override
    public void save(Usuario usuario) {
        List<UsuarioRol> lista;
        UsuarioRol rol;
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            lista = new ArrayList<>();

            rol = new UsuarioRol();
            rol.setAuthority("ROLE_USER");
            rol.setUsuario(usuario);
            lista.add(rol);

            usuario.setRoles(lista);
        }
        usuario.setPassword(DigestUtils.md5Hex(usuario.getPassword()));
        super.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        super.update(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario c;
        c = this.findById(id);
        super.delete(c);
    }

}
