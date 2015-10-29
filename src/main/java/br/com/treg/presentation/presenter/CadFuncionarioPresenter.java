/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.FuncionarioBO;
import br.com.treg.business.model.Funcionario;
import br.com.treg.presentation.view.CadFuncionarioView;
import br.com.treg.presentation.view.impl.CadFuncionarioViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadFuncionarioPresenter implements CadFuncionarioView.CadFuncionarioViewListener{
    
    CadFuncionarioView view;
    
    FuncionarioBO bo;

    public CadFuncionarioPresenter() {
        view = new CadFuncionarioViewImpl();
        this.view.addListener(this);
        
        bo = new FuncionarioBO();
        this.view.populaListaFuncionarios(bo.listAll());
    }

    @Override
    public CadFuncionarioView getView() {
        return this.view;
    }

    @Override
    public void salvar(Funcionario funcionario) {
        try{
            bo.saveOrUpdate(funcionario);
            this.view.sucesso("Funcionário salvo com sucesso!");
        }catch(RuntimeException re){
            this.view.falha("Falha ao salvar Funcionário");
        }
        
        this.view.populaListaFuncionarios(bo.listAll());
    }

    @Override
    public void excluir(Funcionario funcionario) {
        try{
            bo.delete(funcionario);
            this.view.sucesso("Funcionário excluido com sucesso!");
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar exclusão! Contate o administrador!");
        }
        
        this.view.populaListaFuncionarios(bo.listAll());
    }
    
}
