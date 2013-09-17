package controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import dao.cadastro.UsuarioDaoImpl;

import utils.Mensagens;

import modelo.Usuario;

@ManagedBean(name = "BeanUsuario")
@SessionScoped
public class BeanUsuario {
	private static final String INDEX_GESTOR = "/index-gestor.jsf?faces-redirect=true";
	private static final String INDEX_ALUNO = "/index-aluno.jsf?faces-redirect=true";
	private static final String LOGIN = "/login.jsf?faces-redirect=true";
	private Usuario usuario;
	private Usuario usuarioLocal;
	private UsuarioDaoImpl daoEntity = new UsuarioDaoImpl();
	private boolean skip; 
	private String textoDoBotao = "";
	private String busca = "";
	private List<Usuario> lista; 
	


	public BeanUsuario() {
		usuario = new Usuario();
		usuarioLocal = new Usuario();
		usuario.setUsuario("");
		usuario.setSenha("");
	}

	public void metodo() {
		System.out.println("Metodo:");
	}
	
	//Metodos de login
	public String login(){
		System.out.println("logando");
		Usuario usuarioLocalTemp = daoEntity.findUser(usuarioLocal.getUsuario(), usuarioLocal.getSenha());
		if(usuarioLocalTemp != null){
			usuarioLocal = usuarioLocalTemp;
			if(usuarioLocal.getTipo().equalsIgnoreCase("Gestor")){
				System.out.println("Usuario cadastrado como Gestor");
				return INDEX_GESTOR;
			}else{
				new Mensagens().addMessage("Usuario cadastrado como ALUNO");
				System.out.println("Usuario cadastrado como ALUNO");
				return INDEX_ALUNO;
			}
		}else{
			System.out.println("Usuario nao encontrado");
			new Mensagens().addMessage("Usu‡rio n‹o encontrado");
			return "";
		}
		
	}
	
	public String sair(){
		usuarioLocal = new Usuario();
		usuario.setUsuario("");
		usuario.setSenha("");
		return LOGIN;
	}
	
    public String onFlowProcess(FlowEvent event) {  
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    } 

	public String incAltInserir(){
		textoDoBotao = "Inserir";
		usuario = new Usuario();
		return "incAlt-usuarios.jsf?faces-redirect=true";
	}
	
	public String incAlt() {
		if(textoDoBotao.equalsIgnoreCase("inserir")){
			if(!daoEntity.verificaUsuario(usuario.getUsuario())){
				daoEntity.insert(usuario);
				textoDoBotao = "listagem-usuarios";
				listagem();
				return "listagem-usuarios.jsf?faces-redirect=true";
			}else{
				new Mensagens().addMessage("Usu‡rio: " + usuario.getUsuario() + " j‡ cadastrado!");
				return "";
			}
		}else{
			daoEntity.update(usuario);
			lista = daoEntity.findAll();
			textoDoBotao = "listagem-usuarios";
			listagem();
			return "listagem-usuarios.jsf?faces-redirect=true";
		}
	}
	
	public String listagem() {
		setLista(daoEntity.findAll());
		textoDoBotao = "listagem-usuarios";
		return "listagem-usuarios.jsf?faces-redirect=true";
	}
	
	public String voltar(){
		if(textoDoBotao.equalsIgnoreCase("inserir") || textoDoBotao.equalsIgnoreCase("listagem-usuarios") ){
			return "gerenciar-usuarios.jsf?faces-redirect=true";
		}else{
			listagem();
			textoDoBotao = "listagem-usuarios";
			return "listagem-usuarios.jsf?faces-redirect=true";
		}
		
	}

	public String buscar(){
		setLista(daoEntity.findEspecific(busca));
		textoDoBotao = "listagem-usuarios";
	
		return "listagem-usuarios.jsf?faces-redirect=true";
	}
	
	public String excluir(){
		//System.out.println("Excluindo");
		textoDoBotao = "listagem-usuarios";

		try {
			daoEntity.delete(usuario.getId());
		} catch (Exception e) {
			new Mensagens().addMessage("Erro ao remover: " + e.getMessage());
			return null;
		}finally{
			lista = daoEntity.findAll();			
		}
		return "listagem-usuarios.jsf?faces-redirect=true";
	}	
	
	public String incAltEditar(){
		textoDoBotao = "Atualizar";
		usuario = daoEntity.findById(usuario.getId());
		return "incAlt-usuarios.jsf?faces-redirect=true";
	}
//////////////////////////////////////////////////////////////////
////////////GETTERS AND SETTERS          /////////////////////////
//////////////////////////////////////////////////////////////////
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	public String getTextoDoBotao() {
		return textoDoBotao;
	}

	public void setTextoDoBotao(String textoDoBotao) {
		this.textoDoBotao = textoDoBotao;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Usuario getUsuarioLocal() {
		return usuarioLocal;
	}

	public void setUsuarioLocal(Usuario usuarioLocal) {
		this.usuarioLocal = usuarioLocal;
	}
}
