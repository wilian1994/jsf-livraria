package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {

	public List<Venda> getVendas( long seed ) {

		List<Livro> livros = new DAO<Livro>( Livro.class ).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random( seed );

		for ( Livro livro : livros ) {
			Integer quantidade = random.nextInt( 500 );
			vendas.add( new Venda( livro, quantidade ) );
		}

		return vendas;
	}

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel( "Vendas 2016" );

		List<Venda> vendas = getVendas( 1234 );

		for ( Venda venda : vendas ) {
			vendaSerie.set( venda.getLivro().getTitulo(), venda.getQuantidade() );
		}

		model.addSeries( vendaSerie );

		// código omitido...
		model.setTitle( "Vendas" ); // setando o título do gráfico
		model.setLegendPosition( "ne" ); // nordeste

		// pegando o eixo X do gráfico e setando o título do mesmo
		Axis xAxis = model.getAxis( AxisType.X );
		xAxis.setLabel( "Título" );

		// pegando o eixo Y do gráfico e setando o título do mesmo
		Axis yAxis = model.getAxis( AxisType.Y );
		yAxis.setLabel( "Quantidade" );

		return model;

	}
}
