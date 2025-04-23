#!/bin/bash

# === CONFIGURACIÓN DINÁMICA ===
USER_WIN=$(whoami)
PEM_PATH="/c/Users/$USER_WIN/.ssh/aws-key.pem"
AWS_USER=ubuntu
AWS_HOST=3.221.98.93
REMOTE_DIR=/home/ubuntu/movil

# === Buscar archivos modificados con Git ===
MODIFIED_FILES=$(git status -s | awk '{print $2}')

# === Subir archivos modificados ===
echo "📤 Subiendo archivos modificados (MOVIL) a AWS desde $USER_WIN..."
for FILE in $MODIFIED_FILES; do
  if [ -f "$FILE" ]; then
    echo "  → $FILE"
    rsync -avz -e "ssh -i $PEM_PATH" "$FILE" "$AWS_USER@$AWS_HOST:$REMOTE_DIR/$FILE"
  fi
done

echo "✔️ Subida completada a /movil"
