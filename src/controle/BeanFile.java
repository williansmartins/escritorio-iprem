package controle;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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
import javax.imageio.ImageIO;

import modelo.FileModel;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import utils.Mensagens;
import utils.Redimensionador;
import dao.cadastro.FileDaoImpl;

@ManagedBean(name = "BeanFile")
@SessionScoped
public class BeanFile {
	//////////////////////////////////////////////////////////////////
	////////////ATRIBUTOS
	//////////////////////////////////////////////////////////////////
	private FileModel entidade;
	private String busca;
	private FileDaoImpl daoEntity = new FileDaoImpl();
	private List<FileModel> lista; 
	private String destination="/Users/williansmartins/Documents/workspace/iprem/WebContent/uploads/";
	FileUploadEvent event;
	private UploadedFile file;
	BufferedImage imagem;
	String localThumb = "//images//uploads//thumbs//";
	String localFile = "//images//uploads//originals//";
	
	//////////////////////////////////////////////////////////////////
	////////////CONSTRUTOR
	//////////////////////////////////////////////////////////////////
	
	public BeanFile() {
		entidade = new FileModel();
		entidade.setUrl("escolha.jpg");
	}
	
	//////////////////////////////////////////////////////////////////
	////////////METODOS DE CONTROLE E NAVEGACAO
	//////////////////////////////////////////////////////////////////
	public void escolherFoto(FileUploadEvent event) { 
		this.event = event;
		entidade = new FileModel();
		entidade.setUrl(event.getFile().getFileName());
		try {
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			File result = new File(extContext.getRealPath(localThumb + event.getFile().getFileName()));
			
			System.out.println(extContext.getRealPath	(localThumb  + event.getFile().getFileName()));
			
			salvarThumb( event.getFile().getInputstream(), result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
   
	public void salvarThumb(InputStream in, File file) {
    	try {
    		
    		OutputStream out = new FileOutputStream(file);
    		
    		int read = 0;
    		byte[] bytes = new byte[1024];
    		
    		while ((read = in.read(bytes)) != -1) {
    			out.write(bytes, 0, read);
    		}
    		
    		in.close();
    		out.flush();
    		out.close();
    		
    		mudarEscala(file.getName());
    		
    		FacesMessage msg = new FacesMessage("Imagem escolhida: ", event.getFile().getFileName());  
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    	} catch (IOException e) {
    		FacesMessage msg = new FacesMessage("Erro ao escolher a imagem: ", event.getFile().getFileName());  
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		System.out.println("Erro ao escolher a imagem: " + event.getFile().getFileName() + e.getMessage());
    	}	
    }
    
	public void mudarEscala(String nome) throws IOException{
    	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		File file = new File(extContext.getRealPath(localThumb + nome));
		
    	BufferedImage in2 = javax.imageio.ImageIO.read(file);
        BufferedImage out = Redimensionador.scaleImage(in2, BufferedImage.TYPE_INT_ARGB, 100, 800);
        
		ImageIO.write(out, "PNG", file);
		in2.flush();
		out.flush();
		
    }
    
	public void handleFileUpload(FileUploadEvent event) { 
		this.event = event;
		System.out.println("setando nome e event" + event.getFile().getFileName());
		entidade.setUrl(event.getFile().getFileName());
		
		try {
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			File result = new File(extContext.getRealPath("//uploads//" + event.getFile().getFileName()));
			System.out.println(extContext.getRealPath	("//uploads//" + event.getFile().getFileName()));
			
			copyFile(event.getFile().getFileName(), event.getFile().getInputstream(), result);
			
			daoEntity.insert(entidade);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}  
	
	public void handleFileUpload2() { 
		try {
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			File result = new File(extContext.getRealPath(localFile + event.getFile().getFileName()));
			System.out.println(extContext.getRealPath	(localFile + event.getFile().getFileName()));
			
			copyFile(event.getFile().getFileName(), event.getFile().getInputstream(), result);
			
			daoEntity.insert(entidade);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage("Erro ao persistir imagem");  
    		FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println("Erro ao persistir imagem");
		}
	}  
	
	public void salva(){
		System.out.println("salvando entidade e enviando arquivo");
		System.out.println(entidade.getTitulo());
		System.out.println(entidade.getUrl());
		try {
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			File result = new File(extContext.getRealPath("//images//uploads//" + event.getFile().getFileName()));
			System.out.println(extContext.getRealPath	("//images//uploads//" + event.getFile().getFileName()));
			
			copyFile(event.getFile().getFileName(), event.getFile().getInputstream(), result);
			
			daoEntity.insert(entidade);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void salva2(){
		System.out.println("salvando entidade e enviando arquivo");
		System.out.println(entidade.getTitulo());
		System.out.println(entidade.getUrl());
		try {
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			File result = new File(extContext.getRealPath("//images//uploads//" + file.getFileName()));
			System.out.println(extContext.getRealPath	("//images//uploads//" + file.getFileName()));
			
			copyFile2( file.getInputstream(), result);
			
			daoEntity.insert(entidade);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

    public void copyFile(String fileName, InputStream in, File file) {
    	try {
    		
    		// write the inputStream to a FileOutputStream
    		OutputStream out = new FileOutputStream(file);
    		//OutputStream out = new FileOutputStream(new File(destination + fileName));
    		
    		int read = 0;
    		byte[] bytes = new byte[1024];
    		
    		while ((read = in.read(bytes)) != -1) {
    			out.write(bytes, 0, read);
    		}
    		
    		in.close();
    		out.flush();
    		out.close();
    		
    		FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " foi upado.");  
    		FacesContext.getCurrentInstance().addMessage(null, msg);
    		System.out.println("Arquivo criado!");
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
    public void copyFile2( InputStream in, File file) {
    	try {
    		
    		// write the inputStream to a FileOutputStream
    		OutputStream out = new FileOutputStream(file);
    		//OutputStream out = new FileOutputStream(new File(destination + fileName));
    		
    		int read = 0;
    		byte[] bytes = new byte[1024];
    		
    		while ((read = in.read(bytes)) != -1) {
    			out.write(bytes, 0, read);
    		}
    		
    		in.close();
    		out.flush();
    		out.close();
    		
//            FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " foi upado.");  
//            FacesContext.getCurrentInstance().addMessage(null, msg);
    		System.out.println("Arquivo criado!");
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
 	
    public void listagem() {
    	setLista(daoEntity.findAll());
    }
	
    public void buscar(){
    	setLista(daoEntity.findEspecific(busca));
    }
    
    public void excluir(){
    	try {
    		daoEntity.delete(entidade.getId());
    	} catch (Exception e) {
    		System.out.println("Erro ao remover: " + e.getMessage());
    		new Mensagens().addMessage("Erro ao remover: " + e.getMessage());
    	}finally{
    		setLista(daoEntity.findAll());			
    	}
    }	
    
    public void incAltEditar(){
    	entidade = daoEntity.findById(entidade.getId());
    }
	
	//////////////////////////////////////////////////////////////////
	////////////GETTERS AND SETTERS          
	//////////////////////////////////////////////////////////////////
	public FileModel getEntidade() {
		return entidade;
	}

	public void setEntidade(FileModel entidade) {
		this.entidade = entidade;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<FileModel> getLista() {
		return lista;
	}

	public void setLista(List<FileModel> lista) {
		this.lista = lista;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
