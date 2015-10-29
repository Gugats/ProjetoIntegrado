/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.FornecedorBO;
import br.com.treg.business.bo.NotaFiscalBO;
import br.com.treg.business.bo.ObraBO;
import br.com.treg.business.model.NotaFiscal;
import br.com.treg.presentation.view.CadNotaFiscalView;
import br.com.treg.presentation.view.impl.CadNotaFiscalViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadNotaFiscalPresenter implements CadNotaFiscalView.CadNotaFiscalViewListener{

    CadNotaFiscalView view;
    
    NotaFiscalBO bo;
    
    ObraBO obraBO;
    
    FornecedorBO fornecedorBO;
    
    public CadNotaFiscalPresenter() {
        view = new CadNotaFiscalViewImpl();
        this.view.addListener(this);
        
        bo = new NotaFiscalBO();
        obraBO = new ObraBO();
        fornecedorBO = new FornecedorBO();
        
        this.view.populaComboFornecedores(fornecedorBO.listAll());
        this.view.populaComboObras(obraBO.listAll());
        this.view.populaListaNF(bo.listAll());
        
    }

    @Override
    public CadNotaFiscalView getView() {
        return this.view;
    }

    @Override
    public void salvar(NotaFiscal nf) {
        try{
            bo.saveOrUpdate(nf);
            this.view.sucesso("Nota Fiscal salva com sucesso!");
        }catch(RuntimeException re){
            this.view.falha("Falha ao salvar Nota Fiscal");
        }
        
        this.view.populaListaNF(bo.listAll());
    }

    @Override
    public void excluir(NotaFiscal nf) {
        try{
            bo.delete(nf);
            this.view.sucesso("Nota Fiscal excluida com sucesso!");
        }catch(RuntimeException e){
            this.view.falha("Ocorreu um erro ao realizar exclusão! Contate o administrador!");
        }
        
        this.view.populaListaNF(bo.listAll());
    }
    
}
