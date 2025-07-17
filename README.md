# 📱 Fitness Tracker

Um aplicativo Android simples e didático para calcular e registrar diferentes métricas corporais: **IMC**, **TMB**, **GET** e **%Gordura Corporal**.

---

## 🎯 Funcionalidades

- ✅ Cálculo de **IMC**, **TMB**, **GET** e **PGC**
- ✅ Salvamento automático dos resultados no banco de dados local
- ✅ Exibição dos resultados em uma lista por categoria
- ✅ Edição ou exclusão de registros via clique longo
- ✅ Interface simples e responsiva
- ✅ Menu superior para busca de resultados

---

## 📸 Tela Principal

A tela principal apresenta botões com ícones e cores distintas para cada cálculo.

---

## 🧮 Cálculos Suportados

- **IMC**: Índice de Massa Corporal
- **TMB**: Taxa Metabólica Basal
- **GET**: Gasto Energético Total
- **PGC**: Percentual de Gordura Corporal

---

## 💾 Banco de Dados

Utiliza **Room** como ORM para persistência local.
Todos os resultados calculados podem ser editados ou excluídos.

---

## 🔄 Fluxo de Edição

1. Usuário faz um **clique longo** em um item da lista.
2. É exibido um diálogo com as opções: `Editar` ou `Excluir`.
3. A opção `Editar` leva de volta à tela de cálculo, preenchendo os dados com base no ID.
4. Ao recalcular, o valor anterior é sobrescrito no banco.

---

## 📂 Estrutura de Pacotes

```
├── activities/           # Telas principais da aplicação (IMC, TMB, GET, etc.)
│   ├── GetActivity.kt
│   ├── ImcActivity.kt
│   ├── ListCalcActivity.kt
│   ├── MainActivity.kt
│   ├── PgcActivity.kt
│   └── TmbActivity.kt
│
├── model/                # Estruturas de dados e camada de persistência
│   ├── App.kt
│   ├── AppDatabase.kt
│   ├── Calc.kt
│   ├── CalcDao.kt
│   ├── DateConverter.kt
│   └── Gender.kt
│
├── utils/                # Utilitários diversos da aplicação
│   ├── calculators/      # Cálculos e avaliações de saúde
│   │   ├── Calculator.kt
│   │   ├── HealthEvaluator.kt
│   │   └── PhysicalActivityLevel.kt
│   │
│   ├── helpers/          # Auxiliares de navegação, diálogos e armazenamento
│   │   ├── DialogHelper.kt
│   │   ├── NavigationHelper.kt
│   │   └── SaveHelper.kt
│   │
│   ├── ui/               # Componentes de interface reutilizáveis
│   │   ├── MainItem.kt
│   │   └── OnItemClickListener.kt
│   │
│   └── validators/       # Validações de input do usuário
│       └── InputValidator.kt
```

---

## 🧠 Padrões de Projeto Utilizados

- **MVC (Model-View-Controller)**: Separação entre as atividades (View), lógica de cálculo e validação (Controller), e camada de dados com Room (Model).
- **Singleton**: Aplicado na criação da instância única do banco de dados (`AppDatabase`).
- **Repository Pattern**: Centraliza o acesso à fonte de dados, separando a lógica de acesso do restante da aplicação.
- **Adapter Pattern**: Utilizado nos adaptadores das listas para exibir os resultados armazenados.
- **Strategy Pattern** *(parcial)*: Diferentes tipos de cálculos encapsulados em classes distintas (`Calculator.kt`, `HealthEvaluator.kt`, etc).

---

## 🖼️ Screenshots

<p align="center">
  <img src="assets/screenshot.png" width="45%" alt="Tela Principal"/>
  <img src="assets/screenshot_2.png" width="45%" alt="Tela Principal /2"/>
</p>

<p align="center">
  <img src="assets/screenshot_3.png" width="45%" alt="Tela de Entrada de Dados (Input)"/>
  <img src="assets/screenshot_4.png" width="45%" alt="Tela de Resultados com Edição/Exclusão"/>
</p>


## 🛠️ Tecnologias Utilizadas

- Kotlin
- Android SDK
- Room (Jetpack)
- RecyclerView
- Material Design
- XML Layouts
- SQLite
- Threads e Handlers
- Context e Intents

---

## 🧑‍💻 Autor

**Tiago Marcial**

> Projeto desenvolvido para fins de estudo e prática com Android.  
> Feedbacks e sugestões são bem-vindos!

[🔗 LinkedIn](https://linkedin.com/in/tiago-marcial)  
[📧 Email](mailto:tiago.127@gmail.com)  
