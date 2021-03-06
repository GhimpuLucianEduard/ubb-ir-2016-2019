#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>

int s;

int deservire_client(uint16_t n, struct sockaddr_in *client) {
  uint16_t suma = 0, r;
  int s2, l;
  struct sockaddr_in server;

  suma = suma + ntohs(n);

  s2 = socket(AF_INET, SOCK_DGRAM, 0);
  if (s2 < 0) {
    return 1;
  }

  memset(&server, 0, sizeof(server));
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;

  do {
    r = random() % (65536 - 1024) + 1024;
    server.sin_port = htons(r);
  }
  while (bind(s2, (struct sockaddr *) &server, sizeof(server)) < 0);

  r = htons(r);
  sendto(s, &r, sizeof(r), 0, (struct sockaddr *) client, sizeof (struct sockaddr_in));

  l = sizeof(client);
  memset(&client, 0, sizeof(client));

  do {
    recvfrom(s2, &n, sizeof(n), MSG_WAITALL, (struct sockaddr *) client, &l);
    n = ntohs(n);
    suma += n;
    printf("Am primit numarul %hu de la un client\n", n);
  } while (n != 0);

  printf("Am calculat suma finala %hu\n", suma);

  close(s2);
  return 0;
}

int main(int argc, char **argv) {
  struct sockaddr_in server, client;
  int l;
  uint16_t n;

  if (argc < 2) {
    fprintf(stderr, "Trebuie sa dati ca parametru portul.\n");
    return 1;
  }

  s = socket(AF_INET, SOCK_DGRAM, 0);
  if (s < 0) {
    printf("Eroare la crearea socketului server\n");
    return 1;
  }

  memset(&server, 0, sizeof(server));
  server.sin_port = htons(atoi(argv[1]));
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;

  if (bind(s, (struct sockaddr *) &server, sizeof(server)) < 0) {
    printf("Eroare la bind\n");
    return 1;
  }

  l = sizeof(client);
  memset(&client, 0, sizeof(client));

  while (1) {

    recvfrom(s, &n, sizeof(n), MSG_WAITALL, (struct sockaddr *) &client, &l);
    if (fork() == 0) {
       return deservire_client(n, &client);
    }

  }

  close(s);
}