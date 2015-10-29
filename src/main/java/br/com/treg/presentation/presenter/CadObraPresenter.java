/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.ClienteBO;
import br.com.treg.business.bo.FuncionarioBO;
import br.com.treg.business.bo.ObraBO;
import br.com.treg.business.model.Obra;
import br.com.treg.presentation.view.CadObraView;
import br.com.treg.presentation.view.impl.CadObraViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadObraPresenter implements CadObraView.CadObraViewListener{

    CadObraView view;
    
    ObraBO bo;
    
    ClienteBO clienteBO;
    
    FuncionarioBO funcionarioBO;
    
    public CadObraPresenter() {
        view = new CadObraViewImpl();
        this.view.addListener(this);
        
        bo = new ObraBO();
        clienteBO = new ClienteBO();
        funcionarioBO = new FuncionarioBO();
        
        this.view.populaComboClientes(clienteBO.listAll());
        this.view.populaListaObras(bo.listAll());
        this.view.populaListaFuncionarios(funcionarioBO.listAll());
    }

    @Override
    public CadObraView getView() {
        return this.view;
    }

    @Override
    public void salvar(Obra obra) {
//        try{
            bo.saveOrUpdate(obra);
//            this.view.sucesso("Obra salva com sucesso!");
//        }catch(RuntimeException re){
//            this.view.falha("Falha ao salvar Obra");
//        }
        this.view.populaListaObras(bo.listAll());
    }

    @Override
    public void excluir(Obra obra) {
         try{
            bo.delete(obra);
            this.view.sucesso("Obra excluída com sucesso!");
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar exclusão! Contate o administrador!");
        }
        this.view.populaListaObras(bo.listAll());
    }

    @Override
    public void getListaFuncionarios() {
        this.view.populaListaFuncionarios(funcionarioBO.listAll());
    }

    
    
}
