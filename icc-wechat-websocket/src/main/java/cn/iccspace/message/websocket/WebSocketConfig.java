package cn.iccspace.message.websocket;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/**
 * WebScoket配置处理器
 * 
 * @author Goofy
 * @Date 2015年6月11日 下午1:15:09
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	@Resource
	MyWebSocketHandler handler;
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ws").addInterceptors(new MyHandShakeInterceptor());
		registry.addHandler(handler, "/ws/sockjs").addInterceptors(new MyHandShakeInterceptor()).withSockJS();
	}
}