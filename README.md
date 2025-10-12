🧩 CRUD Multi-Linguagens
📖 Descrição

Este projeto tem como objetivo criar um CRUD completo nas linguagens de backend mais utilizadas atualmente.
Como desenvolvedor experiente em Java, estou utilizando este projeto para mensurar a curva de aprendizado ao implementar a mesma aplicação em diferentes linguagens, comparando sintaxe, produtividade e boas práticas em cada uma.

🎯 Cenário Proposto
O sistema consiste em um cadastro de usuários com os seguintes elementos:

Entidade: User
- name
- age
- cpf
- addresses (lista de endereços)

Entidade: Address
- dressCode
- street
- number
- city
- state
- country
- complement

⚙️ Funcionalidades do CRUD

Criar usuário (com ou sem endereços)

Buscar usuário por ID
Buscar todos os usuários (com paginação)
Editar usuário
Remover usuário por ID
Adicionar um novo endereço ao usuário
Remover endereço de um usuário

🌐 Chamada Externa
Cada implementação fará uma chamada HTTP externa para validação do CEP, explorando as bibliotecas nativas ou frameworks disponíveis em cada linguagem.

🗄️ Base de Dados
Será utilizada uma base SQL em todas as linguagens, mantendo a mesma estrutura de tabelas e relacionamentos.

🧪 Recursos Técnicos
Cada versão do projeto buscará aplicar:
Validações de dados
Chamada HTTP externa (CEP)
Documentação da API (ex: Swagger/OpenAPI)

💻 Linguagens Utilizadas
As implementações serão feitas nas seguintes linguagens:
- Kotlin
- Python
- Go
- Node.js
- Rust
- C# (.NET)
- PHP

🚀 Progresso
Linguagem	Status	Observações
- Kotlin	🟢 Em andamento	Linguagem inicial (baseada em Java)
- Python	⚪ A iniciar	Próxima linguagem a ser implementada
- Go	⚪ A iniciar	-
- Node.js	⚪ A iniciar	-
- Rust	⚪ A iniciar	-
- C# (.NET)	⚪ A iniciar	-
- PHP	⚪ A iniciar	-

🎓 Objetivo Final
Ao término, será possível comparar e documentar:
- Diferenças de sintaxe e frameworks
- Curva de aprendizado de cada linguagem
- Padrões e boas práticas adotadas
- Desempenho e complexidade de manutenção

🧠 Autor
Afrânio Alves Garcia
- Desenvolvedor backend com foco em APIs modernas, escaláveis e performáticas. Experiência em Java, Azure, mensageria e microsserviços.
