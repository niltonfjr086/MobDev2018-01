package ws_pousada.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cliente extends BaseEntity {
	private static final long serialVersionUID = -835157262846244902L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Column(nullable = true, length = 80)
    private String nome;
    
	@Column(nullable = true, length = 150)
    private String sobrenome;
    
	@Column(nullable = true)
    private Integer sexo;
    
	@Column(nullable = true, length = 14)
    private String cpf;
    
	@Column(nullable = true, length = 15)
    private String telefone;
    
	@Column(nullable = true, length = 15)
    private String celular;
	
	@Column(nullable = true)
    private LocalDate dtNascimento;
    
	@Column(nullable = true, length = 10000)
    private String observacoes;
	
	@Column(nullable = true)
    private Integer nivelAcesso;
	
	@Column(nullable = true, length = 100)
    private String email;
    
	@Column(nullable = true, length = 150)
    private Endereco endereco;

    

    

    
    //PREENCHER APENAS AO BUSCAR ASSOCIACOES CLIENTE_ESTADIA
    private Long idAssociacao;
    
    private Boolean isResponsavel;
    // possui lista de dependentes

    
    public Cliente() {
    	super();
    }
    
    
    public Long getId() {
        return id;
    }


	public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDtNascimento() {
        
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Integer getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Long getIdAssociacao() {
        return idAssociacao;
    }

    public void setIdAssociacao(Long idAssociacao) {
        this.idAssociacao = idAssociacao;
    }

    public Boolean getIsResponsavel() {
        return isResponsavel;
    }

    public void setIsResponsavel(Boolean isResponsavel) {
        this.isResponsavel = isResponsavel;
    }

}
