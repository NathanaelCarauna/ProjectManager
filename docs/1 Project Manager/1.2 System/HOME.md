# 1.2 System

![diagram](https://www.plantuml.com/plantuml/svg/0/fPJ1Rjim38Rl1VeEN0vR2hJnPQUTsYR1sg5fWZbTiLD0osoYLfOCYKvM37ltKNAIEKEs7PP3B9E_nOyKgSl0wBcfpEd96ssLQKg25NCTFcIPnyrWgNdL54qWhvnbiZnGhigmz1hjsZHramtVNymCMdwOt6K6cGABQJ4KDMfBVb2dpKzFxgw-JX_cYyVR-StYRlnnV3_gdqNxZ7nmjXz3X7FeFSHdJnOzyUQlWQbQZBzndw9pV56XDX2I-JVHjMjiYVwrZtKjdfbtpwGO9cXnIRvt1jzFJq1-UxB-Xeghf7sa0cHbj4BMpiQDdt6DWBQ4lFRQBc7oPHZD8p9wBO30Am8f7Mi5sy8aTJHhUVMM60gqB_7R87-6e1OEm5zX1X7-a2ZKA47oLZ-B-YDOGNbTj-4yeU9Uc-rQbnG0ZJaEyTHO5NT1e_aLs85g0higvhBM6B_H7gGYK7YtaJCuGbUvGXjgIpP9wuWNmt-E770lc6iwn06ZAyrKGcYA83YE_mEl1TWzIbfhHKSuooAU538M6AZ5CzXk3Zipv6f55KPlpiuBQcn0AUXI1_P9BP1FpbTfVGuh32j90nj1iRmz4b2UolYAHjeJbQ8GmBYbl12h8sXOQubuDRlzSxznjz_sXVn7mMAD43xbq_liPZwVWKH8qTluadyREGrHJhOCG6cKqgJba_biXxgNfYBAkOhqBMoydA4qlOloJwFhOLU-6wEsUNSJqbKaJvkwZ7UAkKVDky9EX_rD_YCc5lNRIsC_o3lvNjaHjNX9SUZejdNN7OkrkvKEQXRh89fBiMtljdTPDf-EfZ1SaNe1_GJnHkcCEkW0bgHLofy0)

**Level 2: Container diagram**

Once you understand how your system fits in to the overall IT environment, a really useful next step is to zoom-in to the system boundary with a Container diagram. A "container" is something like a server-side web application, single-page application, desktop application, mobile app, database schema, file system, etc. Essentially, a container is a separately runnable/deployable unit (e.g. a separate process space) that executes code or stores data.

The Container diagram shows the high-level shape of the software architecture and how responsibilities are distributed across it. It also shows the major technology choices and how the containers communicate with one another. It's a simple, high-level technology focussed diagram that is useful for software developers and support/operations staff alike.

**Scope**: A single software system.

**Primary elements**: Containers within the software system in scope.
Supporting elements: People and software systems directly connected to the containers.

**Intended audience**: Technical people inside and outside of the software development team; including software architects, developers and operations/support staff.

**Notes**: This diagram says nothing about deployment scenarios, clustering, replication, failover, etc.