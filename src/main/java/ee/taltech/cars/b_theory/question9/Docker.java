package ee.taltech.cars.b_theory.question9;

public class Docker {

    //todo A
    // What is a server?
    // Answer: Server is a computer program or device that provides a service
    // to another computer program and its client. Servers can provide many functionalities,
    // like sharing data or resources among multiple clients, or performing computation for a client.

    //todo B
    // What is the difference between build server and production server?
    // Answer: Difference is mainly in security. Usually build server allows unrestricted access to and
    // control by a user or group of users. A production server is configured to restrict access to
    // authorized users and to limit control to system administrators.

    //todo C
    // What is docker?
    // Answer:Docker is a platform for developing, shipping and running applications.
    // It provides the ability to package and run an application in a loosely isolated environment called a container.

    //todo D
    // Name and explain docker container benefits over virtual machine setup (java jar as system process and installed nginx)
    // 1 With a container environment, multiple workloads can run with 1 OS.
    // 2 Containers are typically much smaller, faster and more flexible, which makes them much
    // better fit for fast development cycles, microservices and its easier to update them(containers).

    //todo E
    // Name and explain docker container drawback over virtual machine setup (java jar as system process and installed nginx)
    // 1 Container uses the kernel of the host OS and has operating system dependencies.
    // Therefore, containers can differ from the underlying OS by dependency, but not by type.
    // The hosts kernel limits the use of other operating systems.

    //todo F
    // Name and describe tools for docker architecture
    // 1
    // 2

    //todo G
    // Name and explain why are companies slow in moving existing systems to docker architecture (do not explain why docker is bad)
    // 1 It is a long process. Big and heavy systems have been configured and tweaked over the years and to switch to Docker
    // simply requires a lot of time which companies would rather spend on something else.
    // 2 Maintaining. Docker containers are isolated and a big application is split up to run on many different containers. It can
    // be difficult for companies to monitor what, when and where something happens.
}
