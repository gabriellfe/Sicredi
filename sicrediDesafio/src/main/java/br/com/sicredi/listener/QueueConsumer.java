package br.com.sicredi.listener;

import java.io.Serializable;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.sicredi.domain.model.Assembleia;

@Component
public class QueueConsumer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RabbitTemplate rabbitTemplate;


	public void send(Assembleia order) {
		rabbitTemplate.convertAndSend("testeFila", order);
	}

	@RabbitListener(queues = { "testeFila" })
	public void receive(@Payload Assembleia fileBody) {
		String resultado = null;
		if(fileBody.getVotoNao() < fileBody.getVotoSim()) {
			resultado = "A pauta foi aprovada !!";
		}else if(fileBody.getVotoNao() > fileBody.getVotoSim()){
			resultado = "A pauta foi negada !!";
		}else {
			resultado = "Houve um empate na pauta !!";
		}
			
		System.out.println("Message "+ resultado + fileBody);
	}

}