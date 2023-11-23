package com.tevology.logistica.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "la.vida.te.da.sorpresas.aldebaran.342";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEAvPPLmlhqi3eWUDYcqxQOwVG61oxiESdHROokopXqV++PiwmH\r\n" + 
			"5+qyNyYGhkwNwkOProHEdtJ2Q7TWqGT6velk2DOrc4JQeJ/dfr6d/t5pYYrV6GUp\r\n" + 
			"VKgJvYgtC5k1t9W82MthgLEH29x4tLvedAxubUMQrNRryyI1ofPcofQf1a2OVkkT\r\n" + 
			"i+EoHrxCZVUVQDGeVLbYKfQ2DIr8rFWByZBBz9G8M2OdrNZPBg8W4gmjnlBDAt3P\r\n" + 
			"DSOEC12ktdmKfK5m8wKk24i79R8/K4nBgGQ+/hyuvpWDXtI6BzApXf052ThrEqVc\r\n" + 
			"BZpe+1sMBI9ujL/U/Kut0du+M+5pfDkDJu9p+wIDAQABAoIBAE+pQReLWy/9dQPQ\r\n" + 
			"LHn79NSQDoNRoC63LpbhWByrs6brOstVc1TVX9lcULfh7JMQRM0/rIKj7HRTE4Tf\r\n" + 
			"Pc8/TOLRteD2+dJBI/LBjn7bafZTwCdALxKf3atodAVFzJ36oKmN5UeKW1PYOZOI\r\n" + 
			"+RpX+15ZPfh/fgqgG5hx5XzqHVnKHbgPjVR+4iQNHRJUTVNEi61yHGQQWRF+KfWI\r\n" + 
			"BLi36yVDj8Tnw48Ypv8hwbVl9STNWAmdxTw9tzNFwsLo03QYwi/HwLVVh9UH/Spo\r\n" + 
			"v5t/UpembiJ9rPtMkg/Z30O+71YdKGBO0UCpjj5XbECDN4LEALJ58+LR6a9k7+Lk\r\n" + 
			"/BUcRsECgYEA8oU20iqXBqN7MZV7OPIUzkjQQjS7l+66dhJclN5eRyaUhepMhynG\r\n" + 
			"U43mbTmpfpjmIgDlvZaUufIMMCE/mZt0132E6esMudfz530yI37j/0TdBaRceV4c\r\n" + 
			"g397rpDeoeCcBPkf+3urs3y2QCQyDE9MDOTZ0IX3iTlmiIrOxSuyW5ECgYEAx3Re\r\n" + 
			"m2ay0ptM6jMIUed5DJYC2K9V4q5cNuK9/vExdigOTTQZb+CCnJOFgv4CK8ccN/VL\r\n" + 
			"vpNh7p0PzR0G/XQYpb8KIcYoiSQes+wt+pUWpG2nJ+YPnA1CWPcciX/h1yHWPywA\r\n" + 
			"4zPn5dk0rk1d2MfE4F0ZgBSZGQp61v5g20lg7ssCgYA3maTs4AjJ49cNkhizAIUW\r\n" + 
			"mgjs9MwBVCD5itIg6y4wH78zSHkND93BMCog5nReas1wBEanhYtJah2zNxwuqj5K\r\n" + 
			"XSOIwXOkc8ceTkydaKz6zB3j+j+dL59X4nVHmzz/XdBHL5pOfJbrym66G9JBkjh8\r\n" + 
			"SEdLkDslAbzpFvG6sH7ukQKBgEM7Q6SoYoDBXpTXLOfG2bPXYRA/Ufj+AxFxLtAf\r\n" + 
			"5ydH60I03JosmHpE880q5/pvvGBUa/rKXUl8YdYnAbMvjhtYoQavb/yCU2Mcqob7\r\n" + 
			"+EzmtWCqmPpmMcYhTdo/ZBYLTteucoIjffipVaunYonvFLr4Rt84kj6xnKSZzHY+\r\n" + 
			"btEvAoGBAJdmqKWiJuRj9uHfyJTuAiafBXBqefo5F6FAAbncWgmCOdvZvs9SeH95\r\n" + 
			"MdFvecK8vY4RBONuOAbLNz9xdYu3uOM5HCb5NpH9bpHAwS6vMFoo1/X01LPCTOq/\r\n" + 
			"+ssoOwpp/Utze1kgm+IqlKejCefezNdHYpNcOIhB22ColBNbTb2k\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvPPLmlhqi3eWUDYcqxQO\r\n" + 
			"wVG61oxiESdHROokopXqV++PiwmH5+qyNyYGhkwNwkOProHEdtJ2Q7TWqGT6velk\r\n" + 
			"2DOrc4JQeJ/dfr6d/t5pYYrV6GUpVKgJvYgtC5k1t9W82MthgLEH29x4tLvedAxu\r\n" + 
			"bUMQrNRryyI1ofPcofQf1a2OVkkTi+EoHrxCZVUVQDGeVLbYKfQ2DIr8rFWByZBB\r\n" + 
			"z9G8M2OdrNZPBg8W4gmjnlBDAt3PDSOEC12ktdmKfK5m8wKk24i79R8/K4nBgGQ+\r\n" + 
			"/hyuvpWDXtI6BzApXf052ThrEqVcBZpe+1sMBI9ujL/U/Kut0du+M+5pfDkDJu9p\r\n" + 
			"+wIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
