package merenda.com.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import merenda.com.demo.modelo.Foto;
import merenda.com.demo.repositorio.FotoRepositorio;


@Service
public class FotoService {

	private static final Logger logger = Logger.getLogger(FotoService.class.getName());

	@Autowired
	private FotoRepositorio fotoRepositorio;

	public Foto gravarFoto(Foto foto) {
		return fotoRepositorio.save(foto);
	}

	/*
	public List<Foto> listarFoto() {
		return fotoRepository.findAll();
	}

	
	public Foto buscarFotoPorId(Long id) {
		Optional<Foto> opt = fotoRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IllegalArgumentException("Usuário com id : " + id + " não existe");
		}
	}

	
	public int apagarFotoServidor(String uri) throws Exception {

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(uri))
				.DELETE().build();
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> responseBody = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
		return responseBody.statusCode();
	}

	*/
	public Map<String, String> salvarFotoServidor(MultipartFile file, String uri) throws Exception {
		byte[] byteArr = file.getBytes();
		HttpEntity httpEntity = MultipartEntityBuilder.create()
				.addBinaryBody("file", byteArr, ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename()).build();

		Pipe pipe = Pipe.open();
		// Pipeline streams must be used in a multi-threaded environment. Using one
		// thread for simultaneous reads and writes can lead to deadlocks.
		new Thread(() -> {
			try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())) {
				// Write the encoded data to the pipeline.
				httpEntity.writeTo(outputStream);

				logger.info("Bytes do arquivo obtidos com sucesso.");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}).start();
		
		

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder(new URI(uri))
				// The Content-Type header is important, don't forget to set it.
				.header("Content-Type", httpEntity.getContentType().getValue())
				// Reads data from a pipeline stream.
				.POST(BodyPublishers.ofInputStream(() -> Channels.newInputStream(pipe.source()))).build();

		HttpResponse<String> responseBody = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = objectMapper.readValue(responseBody.body(), Map.class);
		return map;
	}

/*
	public void apagarFotoPorId(Long id) {
		Foto foto = buscarFotoPorId(id);
		fotoRepository.delete(foto);		
	}*/

}

