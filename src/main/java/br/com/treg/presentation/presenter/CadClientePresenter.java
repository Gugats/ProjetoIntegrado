/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.ClienteBO;
import br.com.treg.business.model.Cliente;
import br.com.treg.presentation.view.CadClienteView;
import br.com.treg.presentation.view.impl.CadClienteViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadClientePresenter implements CadClienteView.CadClienteViewListener {
    
    CadClienteView view;
    
    ClienteBO bo;
    
    public CadClientePresenter(){
        view = new CadClienteViewImpl();
        this.view.addListener(this);
        
        bo = new ClienteBO();
        this.view.populaListaClientes(bo.listAll());
    }
    
    public CadClienteView getView(){
        return this.view;
    }

    @Override
    public void salvar(Cliente cliente) {
        try{
            bo.saveOrUpdate(cliente);
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar operação! Contate o administrador!");
        }
        this.view.sucesso("Cliente salvo com sucesso!");
        this.view.populaListaClientes(bo.listAll());
    }

    @Override
    public void excluir(Cliente cliente) {
        try{
            bo.delete(cliente);
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar operação! Contate o administrador!");
        }
        this.view.sucesso("Cliente excluido com sucesso!");
        this.view.populaListaClientes(bo.listAll());
    }
    
}
