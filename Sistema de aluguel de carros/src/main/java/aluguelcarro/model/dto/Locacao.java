package aluguelcarro.model.dto;

import java.sql.Date;

public class Locacao {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Date dataInicio;
    private Date dataFim;

    public Locacao() {}

    public Locacao(int id, Cliente cliente, Veiculo veiculo, Date dataInicio, Date dataFim) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Locacao(int id, int clienteId, int veiculoId, Date dataInicio, Date dataFim) {
        this.id = id;
        this.cliente = new Cliente();
        this.cliente.setId(clienteId);
        this.veiculo = new Veiculo();
        this.veiculo.setId(veiculoId);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public Date getDataFim() { return dataFim; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }

    // pegar os ids
    public int getClienteId() {
        return cliente != null ? cliente.getId() : 0;
    }

    public int getVeiculoId() {
        return veiculo != null ? veiculo.getId() : 0;
    }
}
