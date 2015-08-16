/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.FornecedorBO;
import br.com.treg.business.model.Fornecedor;
import br.com.treg.presentation.view.CadFornecedorView;
import br.com.treg.presentation.view.CadFornecedorView.CadFornecedorViewListener;
import br.com.treg.presentation.view.impl.CadFornecedorViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadFornecedorPresenter implements CadFornecedorViewListener {
    
    CadFornecedorView view = new CadFornecedorViewImpl();
    FornecedorBO bo;
    
    public CadFornecedorPresenter(){
        this.view.addListener(this);
        bo = new FornecedorBO();
        
        this.view.populaListaFornecedores(bo.listAll());
    }
    
    public CadFornecedorView getView(){
        return this.view;
    }

    @Override
    public void salvar(Fornecedor fornecedor) {
        try{
            bo.saveOrUpdate(fornecedor);
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar operação! Contate o administrador!");
        }
        view.sucesso("Fornecedor salvo com sucesso!");
        this.view.populaListaFornecedores(bo.listAll());
    }

    @Override
    public void excluir(Fornecedor fornecedor) {
        try{
            bo.delete(fornecedor);
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar operação! Contate o administrador!");
        }
        view.sucesso("Fornecedor excluido com sucesso!");
        this.view.populaListaFornecedores(bo.listAll());
    }
    
}
