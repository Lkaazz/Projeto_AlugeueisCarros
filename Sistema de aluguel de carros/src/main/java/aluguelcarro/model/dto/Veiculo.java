package aluguelcarro.model.dto;

public class Veiculo {
    private int id;
    private String modelo;
    private String marca;
    private double precoDiaria;

    public Veiculo() {}

    public Veiculo(int id, String modelo, String marca, double precoDiaria) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.precoDiaria = precoDiaria;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getPrecoDiaria() { return precoDiaria; }
    public void setPrecoDiaria(double precoDiaria) { this.precoDiaria = precoDiaria; }
}
