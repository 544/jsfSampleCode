package my.example.jsf.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class SampleListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SampleBean sample;

	public SampleBean getSample() {
		return sample;
	}

	public void setSample(SampleBean sample) {
		this.sample = sample;
	}
	
}
