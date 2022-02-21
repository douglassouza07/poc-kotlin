# poc-kotlin
Sugestão de POC para utilizar Kafka com Kotlin:



Implementar uma solução que receba um cliente via request para inclusão no banco.
Após a inclusão no banco, enviar um evento para um tópico do kafka avisando que um cliente foi criado.
Depois, ao consumir esse evento de cliente criado para atualizar o status para "ATIVO".



Informações Complementares
- Dados a serem recebidos: nome, CPF/CNPJ, tipo, endereco
- Os dados adicionais devem ser salvos junto com o cliente: id, data de cadastro, status (sempre no cadastro deve ser PRECADASTRO)
- Validações:
- todos os campos são obrigatórios
- não pode existir 2 clientes com o mesmo CPF/CNPJ no banco
- o tipo deve ser PF/PJ
- o endereço não pode ter menos de 10 caracteres
- o nome não pode ter menos de 5 caraceteres
