package br.com.sicredi.rabbitMQ;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {
	
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}
	
	private DirectExchange trocaDireta() {
		return new DirectExchange("amq.direct");
	}
	@PostConstruct
	private void adiciona() {
		Queue filaRabbit = this.fila("testeFila");
		DirectExchange trocaDireta = this.trocaDireta();
		this.amqpAdmin.declareQueue(filaRabbit);
	}

}
